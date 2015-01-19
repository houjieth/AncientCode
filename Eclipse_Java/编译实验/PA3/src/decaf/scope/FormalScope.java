package decaf.scope;

import decaf.ast.StmtBlock;
import decaf.symbol.Function;
import decaf.symbol.Symbol;
import decaf.utils.IndentPrintWriter;

public class FormalScope extends Scope {

	private Function owner;

	private StmtBlock astNode;

	public FormalScope(Function owner, StmtBlock astNode) {
		this.owner = owner;
		this.astNode = astNode;
	}

	@Override
	public Kind getKind() {
		return Kind.FORMAL;
	}

	public Function getOwner() {
		return owner;
	}

	@Override
	public boolean isFormalScope() {
		return true;
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		pw.println("FORMAL SCOPE OF '" + owner.getName() + "':");
		pw.incIndent();
		for (Symbol symbol : symbols.values()) {
			pw.println(symbol);
		}
		astNode.associatedScope.printTo(pw);
		pw.decIndent();
	}
}
