package com.example.pvrecyclerview_adapter_helper

interface OnHandlePagination {
    fun onLoanMore(totalPage: Int, currentPage: Int)
    fun getTotalPage(): Int
}