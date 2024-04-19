package ru.DmN.phtx.docs.utils

inline fun <T, R> List<T>.dropMap(drop: Int, block: (e: T) -> R): MutableList<R> {
    val list = ArrayList<R>()
    for (i in drop until size)
        list += block(this[i])
    return list
}