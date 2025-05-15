 # 2022년 10월 16일에 대여 중인 자동차인 경우 '대여중' 이라고 표시하고,
 # 대여 중이지 않은 자동차인 경우 '대여 가능'을 표시하는 컬럼(컬럼명: AVAILABILITY)을 추가하여 
 # 자동차 ID와 AVAILABILITY 리스트를 출력
 
 # 반납 날짜가 2022년 10월 16일인 경우에도 '대여중'으로 표시
 
 # 결과는 자동차 ID를 기준으로 내림차순 정렬
 
SELECT CH.CAR_ID,
CASE WHEN CH.CAR_ID IN (SELECT CAR_ID
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY as CH
    WHERE '2022-10-16' BETWEEN START_DATE AND END_DATE)
THEN '대여중'
ELSE '대여 가능' END AS 'AVAILABILITY'
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY as CH
GROUP BY CH.CAR_ID
ORDER BY CH.CAR_ID desc;