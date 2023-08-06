import java.util.*

data class LinkedListNode(
    val item: Any,
    var next: LinkedListNode? = null,
    var prev: LinkedListNode? = null
)

fun removeDups(head: LinkedListNode?) {
    val set = HashSet<Any>()
    var node = head

    while (node != null) {
        if (set.contains(node.item)) {
            node.prev = node.next
        } else {
            set.add(node.item)
        }
        node = node.next
    }

    //set buffer가 없다면 O(n^2)으로 모든 두개의 노드를 비교하면서 중복된 값이 있는지 확인
    //똑같이 중복된 값을 찾으면 node.prev = node.next로 삭제
}