import java.util.*
import kotlin.NoSuchElementException

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

fun returnKthToLast(head: LinkedListNode?, k: Int): Any {
    var node = head

    for (i in 0 until k - 1) {
        if (node == null) throw NoSuchElementException()
        node = head?.next
    }

    return node?.item!!
}


fun deleteMiddleNode(head: LinkedListNode?) {
    head ?: return

    var next = head.next

    next ?: return
    next.next ?: return

    head.next = next.next
    next.next?.prev = head

}

fun partition(head: LinkedListNode?, x: Int): LinkedListNode? {
    head ?: return null

    var leftHead: LinkedListNode? = null
    var leftTail: LinkedListNode? = leftHead

    var rightHead: LinkedListNode? = null
    var rightTail: LinkedListNode? = rightHead

    var node = head
    while (node != null) {
        if ((node.item as Int) < x) {
            if (leftHead == null) {
                leftHead = node
                leftTail = leftHead
            } else {
                leftTail?.next = node
                leftTail = node
            }
        } else {
            if (rightHead == null) {
                rightHead = node
                rightTail = rightHead
            } else {
                rightTail?.next = node
                rightTail = node
            }
        }
        node = node.next
    }
    leftTail?.next = rightHead

    if (leftHead == null) return rightHead
    return leftHead
}
