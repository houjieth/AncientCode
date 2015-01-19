package decaf.dataflow;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import decaf.tac.*;

public class FlowGraph {

	private Functy functy;

	private List<BasicBlock> bbs;
	

	public FlowGraph(Functy func) {
		this.functy = func;
		deleteMemo(func);
		bbs = new ArrayList<BasicBlock>();
		markBasicBlocks(func.head);
		gatherBasicBlocks(func.head);
	}

	private void deleteMemo(Functy func) {
		while (func.head != null && func.head.opc == Tac.Kind.MEMO) {
			func.head = func.head.next;
		}
		for (Tac t = func.head; t != null; t = t.next) {
			if (t.opc == Tac.Kind.MEMO) {
				if (t.prev != null) {
					t.prev.next = t.next;
				}
				if (t.next != null) {
					t.next.prev = t.prev;
				}
			}
		}

	}

	private void markBasicBlocks(Tac t) {
		int index = -1;
		boolean atStart = false;

		for (; t != null; t = t.next) {
			t.bbNum = index;
			switch (t.opc) {
			case RETURN:
			case JUMP:
			case JZERO:
				index++;
				atStart = true;
				break;
			case MARK:
				if (!t.label.target) {
					if (t.prev != null) {
						t.prev.next = t.next;
					} else {
						functy.head = t.next;
					}
					if (t.next != null) {
						t.next.prev = t.prev;
					}
				} else {
					if (!atStart) {
						index++;
						t.bbNum = index;
						atStart = true;
					}
				}
				break;
			default:
				atStart = false;
				break;
			}
		}

	}

	private void gatherBasicBlocks(Tac start) {
		BasicBlock current = null;
		Tac nextStart = null;
		Tac end = null;

		while (start != null && start.bbNum < 0) {
			start = start.next;
		}

		for (; start != null; start = nextStart) {
			int bbNum = start.bbNum;
			while (start != null && start.opc == Tac.Kind.MARK) {
				start = start.next;
			}

			if (start == null) {
				current = new BasicBlock();
				current.bbNum = bbNum;
				current.tacChain = null;
				current.endKind = BasicBlock.EndKind.BY_RETURN;
				nextStart = null;
			} else {
				start.prev = null;
				end = start;
				while (end.next != null && end.next.bbNum == start.bbNum) {
					end = end.next;
				}
				nextStart = end.next;
				current = new BasicBlock();
				current.bbNum = bbNum;
				current.tacChain = start;
				switch (end.opc) {
				case RETURN:
					current.endKind = BasicBlock.EndKind.BY_RETURN;
					current.var = end.op0;
					end = end.prev;
					break;
				case JUMP:
					current.endKind = BasicBlock.EndKind.BY_JUMP;
					current.next[0] = current.next[1] = end.label.where.bbNum;
					end = end.prev;
					break;
				case JZERO:
					current.endKind = BasicBlock.EndKind.BY_JZERO;
					current.var = end.op0;
					current.next[0] = end.label.where.bbNum;
					current.next[1] = nextStart.bbNum;
					end = end.prev;
					break;
				default:
					if (nextStart == null) {
						current.endKind = BasicBlock.EndKind.BY_RETURN;
					} else {
						current.endKind = BasicBlock.EndKind.BY_JUMP;
						current.next[0] = current.next[1] = nextStart.bbNum;
					}
				}
				if (end == null) {
					current.tacChain = null;
				} else {
					end.next = null;
				}
			}
			bbs.add(current);
		}
	}

	public Iterator<BasicBlock> iterator() {
		return bbs.iterator();
	}

	public BasicBlock getBlock(int i) {
		return bbs.get(i);
	}

	public int size() {
		return bbs.size();
	}

	public void analyzeLiveness() {
		//compute def and liveUse
		Iterator<BasicBlock> iter = iterator();
		while (iter.hasNext()) {
			iter.next().computeDefAndLiveUse();
		}
		
		//compute liveIn and liveOut
		for(int i=0;i<bbs.size();i++){
			bbs.get(i).liveIn=new TreeSet<Temp>(Temp.ID_COMPARATOR);
		}
		Boolean changed=true;
		while(changed){
			changed=false;
			for(int i=bbs.size()-1;i>=0;i--){
				bbs.get(i).liveOut=new TreeSet<Temp>(Temp.ID_COMPARATOR);
				for(int k=0;k<bbs.get(i).next.length;k++){
					if(bbs.get(i).next[k]!=0)
						bbs.get(i).liveOut.addAll(bbs.get(bbs.get(i).next[k]).liveIn);
				}
				Set<Temp> newLiveIn=new TreeSet<Temp>(Temp.ID_COMPARATOR);
				newLiveIn.addAll(bbs.get(i).liveOut);
				newLiveIn.removeAll(bbs.get(i).def);
				newLiveIn.addAll(bbs.get(i).liveUse);
				if(!newLiveIn.equals(bbs.get(i).liveIn)){
					changed=true;
					bbs.get(i).liveIn.clear();
					bbs.get(i).liveIn.addAll(newLiveIn);
				}
			}
		}
		//TODO:
	}
	

	public void simplify() {
		getBlock(0).inDegree = 1;
		Iterator<BasicBlock> iter = iterator();
		while (iter.hasNext()) {
			BasicBlock bb = iter.next();
			switch (bb.endKind) {
			case BY_JZERO:
				getBlock(bb.next[1]).inDegree++;
			case BY_JUMP:
				getBlock(bb.next[0]).inDegree++;
				break;
			}
		}
		iter = iterator();
		while (iter.hasNext()) {
			BasicBlock bb = iter.next();
			if (bb.inDegree <= 0
					|| (bb.endKind == BasicBlock.EndKind.BY_JUMP && bb.tacChain == null)) {
				bb.cancelled = true;
			}
		}
		iter = iterator();
		while (iter.hasNext()) {
			BasicBlock bb = iter.next();
			if (bb.cancelled || bb.endKind == BasicBlock.EndKind.BY_RETURN) {
				continue;
			}
			BasicBlock trace = getBlock(bb.next[0]);
			while (trace.cancelled) {
				trace = getBlock(trace.next[0]);
			}
			bb.next[0] = trace.bbNum;

			if (bb.endKind == BasicBlock.EndKind.BY_JZERO) {
				trace = getBlock(bb.next[1]);
				while (trace.cancelled) {
					trace = getBlock(trace.next[0]);
				}
				bb.next[1] = trace.bbNum;

				if (bb.next[0] == bb.next[1]) {
					bb.endKind = BasicBlock.EndKind.BY_JUMP;
				}
			} else {
				bb.next[1] = bb.next[0];
			}
		}

		Map<Integer, Integer> newBBNum = new HashMap<Integer, Integer>();
		int sz = 0;
		iter = iterator();
		for (int i = 0; iter.hasNext(); i++) {
			BasicBlock bb = iter.next();
			if (!bb.cancelled) {
				newBBNum.put(i, sz);
				if (i > sz) {
					bbs.set(sz, getBlock(i));
				}
				sz++;
			}
		}
		bbs = bbs.subList(0, sz);
		iter = iterator();
		for (int i = 0; iter.hasNext(); i++) {
			BasicBlock bb = iter.next();
			bb.bbNum = newBBNum.get(bb.bbNum);
			if (bb.endKind != BasicBlock.EndKind.BY_RETURN) {
				bb.next[0] = newBBNum.get(bb.next[0]);
				bb.next[1] = newBBNum.get(bb.next[1]);
			}
		}
	}

	public void printTo(PrintWriter pw) {
		pw.println("FUNCTION " + functy.label.name + " : ");
		for (BasicBlock bb : bbs) {
			bb.printTo(pw);
		}
	}

	public void printLivenessTo(PrintWriter pw) {
		pw.println("FUNCTION " + functy.label.name + " : ");
		for (BasicBlock bb : bbs) {
			bb.printLivenessTo(pw);
		}
	}

	public Functy getFuncty() {
		return functy;
	}

	public static List<FlowGraph> dataflowAnalyze(List<Functy> funcs) {
		List<FlowGraph> gs = new ArrayList<FlowGraph>();
		for (Functy f : funcs) {
			FlowGraph g = new FlowGraph(f);
			g.simplify();
			g.analyzeLiveness();//compute def,liveUse,liveIN and liveOut for each block in current function
			for (BasicBlock bb : g.bbs) {
				bb.analyzeLiveness();//compute liveOut for each tac code in current block
			}
			gs.add(g);
		}
		return gs;
	}
}
