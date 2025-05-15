-- 코드를 작성해주세요
# 물고기 종류 별로 가장 큰 물고기의 ID, 물고기 이름, 길이
# 물고기의 ID 컬럼명은 ID, 이름 컬럼명은 FISH_NAME, 길이 컬럼명은 LENGTH
# 결과는 물고기의 ID에 대해 오름차순 정렬해주세요.
# 단, 물고기 종류별 가장 큰 물고기는 1마리만 있으며 10cm 이하의 물고기가 가장 큰 경우는 없습니다.

SELECT FI.ID, FN.FISH_NAME, FI.LENGTH
FROM FISH_INFO FI
JOIN FISH_NAME_INFO FN ON FI.FISH_TYPE = FN.FISH_TYPE
JOIN (SELECT FISH_TYPE, max(LENGTH) as ML  
      FROM FISH_INFO
      GROUP BY FISH_TYPE) sq
ON FI.FISH_TYPE = sq.FISH_TYPE AND FI.LENGTH = sq.ML
ORDER BY FI.ID;