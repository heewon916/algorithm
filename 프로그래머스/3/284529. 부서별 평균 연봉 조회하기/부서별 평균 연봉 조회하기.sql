-- 코드를 작성해주세요
# 부서별로 부서 ID, 영문 부서명, 평균 연봉을 조회
SELECT T.DEPT_ID, HD.DEPT_NAME_EN, T.AVG_SAL
FROM (SELECT DEPT_ID, ROUND(SUM(SAL)/COUNT(*)) AS AVG_SAL
      FROM HR_EMPLOYEES  
      GROUP BY DEPT_ID
     ) AS T 
     JOIN HR_DEPARTMENT AS HD ON T.DEPT_ID = HD.DEPT_ID
ORDER BY AVG_SAL DESC;

                                         
