-- 코드를 작성해주세요
# 대장균 개체의 크기로 내림차순 정렬했을 때 
# ntile(4) -> case then? 
# id와 분류된 이름을 출력해라 
# id에 대해 오름차순 정렬하기 
SELECT ID, 
    CASE NTILE(4) OVER (ORDER BY SIZE_OF_COLONY DESC) 
        WHEN 1 THEN 'CRITICAL'
        WHEN 2 THEN 'HIGH'
        WHEN 3 THEN 'MEDIUM'
        ELSE 'LOW'
    END AS COLONY_NAME 
FROM ECOLI_DATA
ORDER BY ID; 