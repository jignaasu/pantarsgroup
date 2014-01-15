package org.jss.polytechnic.dao;

public interface Queries {

	String INSERT_STAGING_EXAM_RESULTS = "INSERT INTO STAGING_EXAM_RESULTS(SL_NO, REG_NO,STUDENT_NAME,SEMESTER,EX1,EX2,EX3,EX4,EX5,EX6,EX7,EX8,EX9,EX_TOTAL,IA1,IA2,IA3,IA4,IA5,IA6,IA7,IA8,IA9,IA_TOTAL,TOTAL,RESULT,QP1,QP2,QP3,QP4,QP5,QP6,QP7,QP8,QP9,STATUS) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	String UPDATE_STAGING_EXAM_STATUS = "UPDATE STAGING_EXAM_RESULTS SET STATUS = 1";

	String INSERT_EXAM_RESULTS = "INSERT INTO EXAM_RESULTS SELECT REG_NO,STUDENT_NAME,SEMESTER,EX1,EX2,EX3,EX4,EX5,EX6,EX7,EX8,EX9,EX_TOTAL,IA1,IA2,IA3,IA4,IA5,IA6,IA7,IA8,IA9,IA_TOTAL,TOTAL,RESULT,QP1,QP2,QP3,QP4,QP5,QP6,QP7,QP8,QP9 FROM STAGING_EXAM_RESULTS SER "
			+ "WHERE NOT EXISTS (SELECT NULL FROM EXAM_RESULTS ER WHERE ER.REG_NO = SER.REG_NO AND ER.SEMESTER = SER.SEMESTER) AND STATUS = 0";

	String GET_RESULTS_TO_BE_UPDATED = "SELECT STUDENT_NAME, RESULT,REG_NO,SEMESTER,EX1,EX2,EX3,EX4,EX5,EX6,EX7,EX8,EX9,IA1,IA2,IA3,IA4,IA5,IA6,IA7,IA8,IA9 FROM STAGING_EXAM_RESULTS SER "
			+ "WHERE EXISTS (SELECT NULL FROM EXAM_RESULTS ER WHERE ER.REG_NO = SER.REG_NO AND ER.SEMESTER =SER.SEMESTER) AND STATUS = 0";

	String UPDATE_EXAM_MARKS = "UPDATE EXAM_RESULTS SET EX1=COALESCE(?,EX1),EX2=COALESCE(?,EX2),EX3=COALESCE(?,EX3)"
			+ ",EX4=COALESCE(?,EX4),EX5=COALESCE(?,EX5),EX6=COALESCE(?,EX6),EX7=COALESCE(?,EX7),EX8=COALESCE(?,EX8),EX9=COALESCE(?,EX9)"
			+ ",IA1=COALESCE(?,IA1),IA2=COALESCE(?,IA2),IA3=COALESCE(?,IA3)"
			+ ",IA4=COALESCE(?,IA4),IA5=COALESCE(?,IA5),IA6=COALESCE(?,IA6),IA7=COALESCE(?,IA7),IA8=COALESCE(?,IA8),IA9=COALESCE(?,IA9)"
			+ " WHERE REG_NO = ? AND SEMESTER = ?";

	String UPDATE_EXAM_TOTAL = "update exam_results set ex_total = ex1+ex2+ex3+ex4+ex5+ex6+ex7+ex8+ex9,ia_total = ia1+ia2+ia3+ia4+ia5+ia6+ia7+ia8+ia9,total = ex_total+ia_total";

	String UPDATE_FAIL_EXAM_RESULT = "UPDATE EXAM_RESULTS SET RESULT = ? "
			+ "WHERE (EX1 < ? OR NULLIF(EX1,'') IS NULL) "
			+ "OR (EX2 < ? OR NULLIF(EX2,'') IS NULL) "
			+ "OR (EX3 < ? OR NULLIF(EX3,'') IS NULL) "
			+ "OR (EX4 < ? OR NULLIF(EX4,'') IS NULL) "
			+ "OR (EX5 < ? OR NULLIF(EX5,'') IS NULL) "
			+ "OR (EX6 < ? OR NULLIF(EX6,'') IS NULL) "
			+ "OR (EX7 < ? OR NULLIF(EX7,'') IS NULL) "
			+ "OR (EX8 < ? OR NULLIF(EX8,'') IS NULL) "
			+ "OR (EX9 < ? OR NULLIF(EX9,'') IS NULL) "
			+ "OR (IA1 < ? OR NULLIF(IA1,'') IS NULL) "
			+ "OR (IA2 < ? OR NULLIF(IA2,'') IS NULL) "
			+ "OR (IA3 < ? OR NULLIF(IA3,'') IS NULL) "
			+ "OR (IA4 < ? OR NULLIF(IA4,'') IS NULL) "
			+ "OR (IA5 < ? OR NULLIF(IA5,'') IS NULL) "
			+ "OR (IA6 < ? OR NULLIF(IA6,'') IS NULL) "
			+ "OR (IA7 < ? OR NULLIF(IA7,'') IS NULL) "
			+ "OR (IA8 < ? OR NULLIF(IA8,'') IS NULL) "
			+ "OR (IA9 < ? OR NULLIF(IA9,'') IS NULL) ";

	String UPDATE_PASS_EXAM_RESULT = "UPDATE EXAM_RESULTS SET RESULT = ? WHERE RESULT != ?";

}