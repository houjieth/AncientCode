package decaf.ast;

import decaf.Location;
import decaf.utils.IndentPrintWriter;

public class AndExpr extends BinaryOpExpr {

	public AndExpr(Expr left, Expr right, Location location) {
		super(NodeType.AND_EXPR, left, right, location);
	}

	@Override
	public void accept(IASTVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void printTo(IndentPrintWriter pw) {
		binaryOperatorPrintTo(pw, "and");
	}

}
