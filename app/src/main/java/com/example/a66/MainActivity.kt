package com.example.a66

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * 휴대폰의 자판은 컴퓨터 키보드 자판과는 다르게 하나의 키에 여러 개의 문자가 할당될 수 있습니다.
 * 키 하나에 여러 문자가 할당된 경우, 동일한 키를 연속해서 빠르게 누르면 할당된 순서대로 문자가 바뀝니다.
 *
 * 예를 들어, 1번 키에 "A", "B", "C" 순서대로 문자가 할당되어 있다면 1번 키를 한 번 누르면 "A",
 * 두 번 누르면 "B", 세 번 누르면 "C"가 되는 식입니다.
 *
 * 같은 규칙을 적용해 아무렇게나 만든 휴대폰 자판이 있습니다.
 * 이 휴대폰 자판은 키의 개수가 1개부터 최대 100개까지 있을 수 있으며,
 * 특정 키를 눌렀을 때 입력되는 문자들도 무작위로 배열되어 있습니다.
 * 또, 같은 문자가 자판 전체에 여러 번 할당된 경우도 있고,
 * 키 하나에 같은 문자가 여러 번 할당된 경우도 있습니다.
 * 심지어 아예 할당되지 않은 경우도 있습니다.
 * 따라서 몇몇 문자열은 작성할 수 없을 수도 있습니다.
 *
 * 이 휴대폰 자판을 이용해 특정 문자열을 작성할 때,
 * 키를 최소 몇 번 눌러야 그 문자열을 작성할 수 있는지 알아보고자 합니다.
 *
 * 1번 키부터 차례대로 할당된 문자들이 순서대로 담긴 문자열배열 keymap과
 * 입력하려는 문자열들이 담긴 문자열 배열 targets가 주어질 때,
 * 각 문자열을 작성하기 위해 키를 최소 몇 번씩 눌러야 하는지
 * 순서대로 배열에 담아 return 하는 solution 함수를 완성해 주세요.
 *
 * 단, 목표 문자열을 작성할 수 없을 때는 -1을 저장합니다.
 *
 * 제한사항
 * 1 ≤ keymap의 길이 ≤ 100
 * 1 ≤ keymap의 원소의 길이 ≤ 100
 * keymap[i]는 i + 1번 키를 눌렀을 때 순서대로 바뀌는 문자를 의미합니다.
 * 예를 들어 keymap[0] = "ABACD" 인 경우 1번 키를 한 번 누르면 A,
 * 두 번 누르면 B, 세 번 누르면 A 가 됩니다.
 * keymap의 원소의 길이는 서로 다를 수 있습니다.
 * keymap의 원소는 알파벳 대문자로만 이루어져 있습니다.
 * 1 ≤ targets의 길이 ≤ 100
 * 1 ≤ targets의 원소의 길이 ≤ 100
 * targets의 원소는 알파벳 대문자로만 이루어져 있습니다.
 * 입출력 예
 * keymap	targets	result
 * ["ABACD", "BCEFD"]	["ABCD","AABB"]	[9, 4]
 * ["AA"]	["B"]	[-1]
 * ["AGZ", "BSSS"]	["ASA","BGZ"]	[4, 6]
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        class Solution {
            fun solution(keymap: Array<String>, targets: Array<String>): IntArray {
                var answer: IntArray = Array<Int>(targets.size,{0}).toIntArray()

                var map = mutableMapOf<String,Int>()

                var str = ""
                //key : 알파벳 ,value 최초 순서
                for(i in 0..keymap.size-1){

                    for(j in keymap[i].length-1 downTo 0){
                        //기존에 값이 있는지 확인하고 있다면 그값의 최소값을 비교하고 값을넣자
                        str = keymap[i][j].toString()
                        if(map.containsKey(str)){
                            if(map.get(str)!!<=j+1) continue
                        }
                        map.put(str,j+1)
                    }
                }

                //계산된 값들을 이용 하여 결과값 출력
                for(i in 0..targets.size-1){
                    for(j in 0..targets[i].length-1) {
                        if (map.containsKey(targets[i][j].toString())) {
                            answer[i] += map.getValue(targets[i][j].toString())
                        }
                        else{
                            answer[i]=-1
                            break
                        }
                    }
                }

                return answer
            }
        }
        val a = Solution()

        a.solution(arrayOf("BC","CDB"),arrayOf("BB"))//2

        a.solution(arrayOf("ABACD","BCEFD"),arrayOf("ABCD","AABB"))
        a.solution(arrayOf("AA"),arrayOf("B"))
        a.solution(arrayOf("AGZ","BSSS"),arrayOf("ASA","BGZ"))
    }

}