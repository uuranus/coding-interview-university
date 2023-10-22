import java.util.*
import kotlin.NoSuchElementException

interface Queue<E> {

    fun enqueue(value: E)
    fun dequeue(): E
    fun empty(): Boolean
    fun full(): Boolean
}

class LinkedListQueue<E> : Queue<E> {
    private val data = LinkedList<E>()

    override fun enqueue(value: E) {
        data.offerLast(value)
    }

    override fun dequeue(): E {
        return data.pollFirst()
    }

    override fun empty(): Boolean {
        return data.isEmpty()
    }

    //계속 추가할 수 있으니까 항상 false
    override fun full(): Boolean {
        return false
    }
}

class ArrayQueue<E : Any> : Queue<E> {
    private val SIZE = 10000
    private var data: Array<Any> = Array(SIZE) { }

    private var head = 0
    private var tail = 0

    override fun enqueue(value: E) {
        if (full()) throw ArrayIndexOutOfBoundsException()

        data[tail] = value
        tail = (tail + 1) % SIZE
    }

    override fun dequeue(): E {
        if (empty()) throw NoSuchElementException()

        return (data[head] as E).also { head = (head + 1) % SIZE }
    }

    override fun empty(): Boolean {
        return head == tail
    }

    override fun full(): Boolean {
        return (tail + 1) % SIZE == head
    }
}