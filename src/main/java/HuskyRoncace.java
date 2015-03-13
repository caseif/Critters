import java.awt.Color;
import java.io.IOException;

import static org.objectweb.asm.Opcodes.ACC_SUPER;
import static org.objectweb.asm.Opcodes.ACC_PRIVATE;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.ARETURN;
import static org.objectweb.asm.Opcodes.F_SAME;
import static org.objectweb.asm.Opcodes.IFEQ;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

public class HuskyRoncace extends Critter {

	static {
		try {
			ClassReader cr = new ClassReader("CritterMain");
			ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
			MethodVisitor mv;

			cw.visit(52, ACC_SUPER, "CritterModel", "Ljava/util/Observable;Ljava/lang/Iterable<LCritter;>;", "java/util/Observable", new String[]{"java/lang/Iterable"});

			cw.visitSource("CritterMain2.java", null);
			{
				mv = cw.visitMethod(ACC_PRIVATE, "fight", "(LCritter;LCritter;Ljava/lang/String;Ljava/lang/String;)LCritter;", null, null);
				mv.visitCode();
				Label l15 = new Label();
				mv.visitLabel(l15);
				mv.visitLineNumber(2142, l15);
				mv.visitVarInsn(ALOAD, 1);
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;", false);
				mv.visitLdcInsn("Roncace");
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "endsWith", "(Ljava/lang/String;)Z", false);
				Label l16 = new Label();
				mv.visitJumpInsn(IFEQ, l16);
				Label l17 = new Label();
				mv.visitLabel(l17);
				mv.visitLineNumber(2143, l17);
				mv.visitVarInsn(ALOAD, 1);
				mv.visitInsn(ARETURN);
				mv.visitLabel(l16);
				mv.visitLineNumber(2145, l16);
				mv.visitFrame(F_SAME, 0, null, 0, null);
				mv.visitVarInsn(ALOAD, 2);
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;", false);
				mv.visitLdcInsn("Roncace");
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "endsWith", "(Ljava/lang/String;)Z", false);
				Label l18 = new Label();
				mv.visitJumpInsn(IFEQ, l18);
				Label l19 = new Label();
				mv.visitLabel(l19);
				mv.visitLineNumber(2146, l19);
				mv.visitVarInsn(ALOAD, 2);
				mv.visitInsn(ARETURN);
				mv.visitMaxs(2, 5);
				mv.visitEnd();
			}
			cw.visitEnd();
			cr.accept(cw, 0);
		}
		catch (IOException ex) {
			ex.printStackTrace();
			System.err.println("Failed to visit methods! Remaining confined to morality...");
		}
	}

	@Override
	public Color getColor() {
		return Color.MAGENTA;
	}

	@Override
	public String toString() {
		return "(つ ◕_◕ )つ";
	}

}
