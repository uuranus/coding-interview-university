//문자열에 있는 문자가 서로 중복되지 않는가
fun isUnique(s: String): Boolean {

    val alpha = BooleanArray(128)
    for (ch in s) {
        if (alpha[ch.code]) {
            return false
        }
        alpha[ch.code] = true
    }

    return true

}

//s1과 s2의 길이가 같고 알파벳의 숫자가 같으면 재정렬해서 동일한 문자열로 만들 수 있다.
fun checkPermutation(s1: String, s2: String): Boolean {

    if (s1.length != s2.length) return false

    val letters = IntArray(128)
    for (ch in s1) {
        letters[ch.code]++
    }

    for (ch in s2) {
        letters[ch.code]--

        if (letters[ch.code] < 0) return false
    }

    return true
}

//trueLength는 s뒤에 space가 많이 붙어있는 경우 실제 문자열의 끝까지의 길이
fun URLify(s: String, trueLength: Int): String {
    return s.substring(0, trueLength).split(" ").joinToString("%02")
}

//문자열을 재정렬해서 palindrome으로 만들 수 있는가
//문자가 2의 배수씩 있어야 함 - 홀수 길이면 하나는 홀수개여도 인정
fun checkPalindromePermutation(s: String): Boolean {
    val letters = IntArray(26)

    for (ch in s) {
        if (ch in 'a'..'z') {
            letters[ch - 'a']++
        }
    }

    val odd = letters.count { it != 0 && it % 2 == 1 }

    return odd <= 1
}

//두 문자열을 한 문자를 insert, remove, or replace해서 두 문자열이 같게 만들 수 있는가
fun checkOneWay(s1: String, s2: String): Boolean {
    if (s1.length == s2.length) {
        return replaceCharacter(s1, s2)
    }

    if (s1.length == s2.length - 1) {
        return insertCharacter(s1, s2)
    } else if (s1.length == s2.length + 1) {
        return insertCharacter(s2, s1)
    }

    return false
}

private fun replaceCharacter(s1: String, s2: String): Boolean {
    var diff = 0
    for (i in s1.indices) {
        if (s1[i] != s2[i]) diff++
    }

    return diff == 1
}

private fun insertCharacter(s1: String, s2: String): Boolean {
    var s1Idx = 0
    var isDiffFound = false
    for (ch in s2) {
        if (s1Idx >= s1.length) break

        if (ch != s1[s1Idx]) {
            if (isDiffFound) return false
            isDiffFound = true
        } else {
            s1Idx++
        }
    }

    return true
}

//aabcccccaaa -> a2blc5a3
fun compressString(s: String): String {
    var pre = s[0]
    var count = 1

    val sb = StringBuilder()
    for (i in 1 until s.length) {
        if (pre == s[i]) {
            count++
        } else {
            sb.append("$pre$count")
            pre = s[i]
            count = 1
        }
    }

    sb.append("$pre$count")

    return sb.toString()
}

//matrix를 오른쪽으로 90도 회전
fun rotateMatrix(matrix: Array<IntArray>): Array<IntArray> {
    val n = matrix[0].size
    val ans = matrix.map { it.clone() }.toTypedArray()

    for (layer in 0 until n / 2) {
        val last = n - layer - 1
        val start = layer
        val size = last - start + 1

        for (i in start until last) {
            val tempTop = ans[start][i]
            val offset = i - start

            //left ->top
            ans[start][i] = ans[last - offset][start]

            //bottom -> left
            ans[last - offset][start] = ans[last][last - offset]

            //right -> bottom
            ans[last][last - offset] = ans[i][last]

            //top -> right
            ans[i][last] = tempTop
        }
    }

    return ans
}

//기존 matrix가 0인 값을 하나라도 가지고 있었다면 전체가 0으로 세팅
fun setZeroMatrixIfOneExist(matrix: Array<IntArray>) {
    val isZeroExist = matrix.map { it.count { it2 -> it2 == 0 } }.count()

    if (isZeroExist > 0) {
        setMatrixZero(matrix)
    }
}

private fun setMatrixZero(matrix: Array<IntArray>) {

    val n = matrix[0].size
    val m = matrix.size

    for (i in 0 until m) {
        for (j in 0 until n) {
            matrix[i][j] = 0
        }
    }
}

//s1을 rotate해서 s2가 될 수 있는가
fun checkRotateString(s1: String, s2: String): Boolean {
    if (s1.length != s2.length) return false

    for (i in s1.indices) {
        val rotatedS1 = "${s1.substring(i, s1.length)}${s1.substring(0, i)}"
        if (isSubstring(rotatedS1, s2)) return true
    }

    return false
}

private fun isSubstring(s1: String, s2: String): Boolean {
    for (i in s1.indices) {
        if (s1[i] != s2[i]) return false
    }

    return true
}
