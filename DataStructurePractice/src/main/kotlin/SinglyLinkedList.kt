import java.util.LinkedList

class SinglyLinkedList {
    private var head: SinglyListNode? = null
    //private var tail: ListNode? = null

    private var size = 0

    fun size() = size

    fun empty() = size == 0

    fun valueAt(index: Int): Any {
        if (size <= index) throw ArrayIndexOutOfBoundsException()

        var point = head
        for (i in 0 until index) {
            point = head?.next
        }

        return point?.item!!
    }

    private fun nodeAt(index: Int): SinglyListNode? {
        if (size <= index) return null

        var point = head
        for (i in 0 until index) {
            point = head?.next
        }

        return point
    }

    private fun search(value: Any): SinglyListNode? {
        var point = head

        while (point != null) {
            if (point.item == value) return point
            point = point.next
        }

        return null
    }

    fun pushFront(value: Any) {
        val newNode = SinglyListNode(value, head)

        head = newNode
        //tail
        //if(empty()) tail = newNode
        size++

    }

    fun popFront(): Any {
        if (empty()) throw NoSuchElementException()
        val value = head?.item!!
        head = head?.next
        size--
        return value
    }

    fun pushBack(value: Any) {
        if (empty()) {
            pushFront(value)
            return
        }

        var point = head
        while (point?.next != null) {
            point = point.next
        }
        val newNode = SinglyListNode(value)
        point?.next = newNode
        size++

        //tail
        //val newNode = ListNode(value)
        //tail.next = newNode
        //size++
    }


    fun popBack(): Any {
        if (empty()) throw NoSuchElementException()

        var point = head
        for (i in 0 until size - 1) {
            point = point?.next
        }
        val value = point?.next?.item!!
        point.next = null

        //tail
        //tail = point

        return value
    }

    fun front(): Any {
        if (empty()) throw NoSuchElementException()

        return head?.item!!
    }

    fun back(): Any {
        if (empty()) throw NoSuchElementException()

        var point = head
        while (point?.next != null) {
            point = point.next
        }

        return point?.item!!

        //tail
        // return tail?.item!!
    }

    fun insert(index: Int, value: Any) {
        if (size <= index) throw ArrayIndexOutOfBoundsException()
        var point = head

        for (i in 0 until index - 1) {
            point = point?.next
        }

        val next = point?.next
        val newNode = SinglyListNode(value, next)
        point?.next = newNode
        size++
    }

    fun erase(index: Int) {
        if (size <= index) throw NoSuchElementException()

        if (index == size - 1) {
            popBack()
            return
        }
        if (index == 0) {
            popFront()
            return
        }

        var point = head

        for (i in 0 until index - 1) {
            point = point?.next
        }

        point?.next = point?.next?.next

        size--
    }

    fun valueNFromEnd(n: Int): Any {
        if (size < n) throw ArrayIndexOutOfBoundsException()

        val index = size - n
        return valueAt(index)
    }

    fun nodeNFromEnd(n: Int): SinglyListNode? {
        if (size < n) throw ArrayIndexOutOfBoundsException()

        val index = size - n
        return nodeAt(index)
    }

    fun reverse() {
        val newHead = nodeNFromEnd(0)
        var point = newHead
        for (i in 1 until size) {
            val node = nodeNFromEnd(i)
            point?.next = node
            point = point?.next
        }
        //tail = point
        head = newHead
    }

    fun removeValue(value: Any) {
        if (empty()) return

        var point = head

        while (point?.next != null) {
            if (point.next?.item == value) {
                point.next = point.next?.next
                break
            }
            point = point.next
        }
    }

}

data class SinglyListNode(
    val item: Any,
    var next: SinglyListNode? = null
)