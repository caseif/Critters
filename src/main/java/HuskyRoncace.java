import java.awt.Color;
import java.io.IOException;

import static org.objectweb.asm.Opcodes.*;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

public class HuskyRoncace extends Critter {

	static {
		try {
			ClassReader cr = new ClassReader("CritterMain");
			ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
			cw.visit(V1_7, ACC_PUBLIC, "CritterMain", null, "java/lang/Object", null);
			MethodVisitor mv = cw.visitMethod(ACC_PRIVATE, "fight",
					"(LCritter;LCritter;Ljava/lang/String;Ljava/lang.String;)LCritter;",
					null, null);
			mv.visitCode();
			mv.visitVarInsn(ALOAD, 0);
			Label l0 = new Label();
			mv.visitJumpInsn(IFEQ, l0); //TODO: compare parameters
			mv.visitLabel(l0);
			//TODO: return
			mv.visitEnd();
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
