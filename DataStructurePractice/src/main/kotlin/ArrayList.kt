class ArrayList<T : Any> {

    private var data =  Array < Any >(16) { }

    private var size = 0

    fun size(): Int {
        return size
    }

    fun capacity(): Int {
        return data.size
    }

    fun isEmpty(): Boolean {
        return size == 0
    }

    fun isNotEmpty(): Boolean {
        return isEmpty().not()
    }

    fun at(index: Int): T {
        if (index >= size || index < 0) throw ArrayIndexOutOfBoundsException()
        return data[index] as T
    }

    fun push(item: T) {
        checkArrayToResize()

        data[size] = item
        size++
    }

    fun insert(index: Int, item: T) {
        checkArrayToResize()

        shiftRight(index)
        data[index] = item
        size++
    }

    fun prepend(item: T) {
        checkArrayToResize()

        shiftRight(0)
        data[0] = item
        size++
    }

    fun pop(): T {
        if (isEmpty()) throw ArrayIndexOutOfBoundsException()
        size--
        val ret = data[size]
        checkArrayToResize()

        return ret as T
    }

    fun delete(index: Int): T {
        if (index >= size || index < 0) throw ArrayIndexOutOfBoundsException()

        val ret = data[index]
        shiftLeft(index + 1)
        size--

        checkArrayToResize()
        return ret as T
    }

    fun remove(item: T) {
        val index = find(item)

        if (index == -1) throw NoSuchElementException()

        shiftLeft(index + 1)
        size--

        checkArrayToResize()
    }

    fun find(item: T): Int {
        for (i in 0 until size) {
            if (data[i] == item) {
                return i
            }
        }
        return -1
    }

    private fun shiftRight(start: Int) {
        for (i in size downTo start) {
            data[i + 1] = data[i]
        }
    }

    private fun shiftLeft(start: Int) {
        for (i in start until size) {
            data[i - 1] = data[i]
        }
    }

    private fun checkArrayToResize() {
        if (size == capacity()) {
            resize(capacity() * 2)
        } else if (size <= capacity() / 4) {
            resize(capacity() / 2)
        }
    }

    private fun resize(newCapacity: Int) {
        val temp = Array<Any>(newCapacity) {}

        for (i in 0 until size) {
            temp[i] = data[i]
        }

        data = temp
    }
}
