package com.example.androidclass.util


class PhoneNumber {
    private var phoneList: MutableList<Char> = mutableListOf()

    companion object{
        const val PHONE_NUMBER_LENGTH = 9
        const val FORMAT_HELPER_INDEX_5 = 5
        const val FORMAT_HELPER_INDEX_7 = 7
    }

    fun add(index: Int, char: Char) {
        if (Character.isDigit(char)) {
            phoneList.add(index, char)
        }
    }

    fun remove(index: Int) {
        phoneList.removeAt(index)
    }

    fun format(): String {
        val stringBuilder = StringBuilder()
        phoneList.forEachIndexed { i, c ->
            if (i == 2) {
                stringBuilder.append(") - ")
            } else if (i == FORMAT_HELPER_INDEX_5 || i == FORMAT_HELPER_INDEX_7) {
                stringBuilder.append(" - ")
            }
            stringBuilder.append(c)
        }
        return stringBuilder.toString()
    }

    fun unformat(): String {
        return String(phoneList.toCharArray())
    }

    fun isValid(): Boolean {
        return phoneList.size == PHONE_NUMBER_LENGTH
    }
}
