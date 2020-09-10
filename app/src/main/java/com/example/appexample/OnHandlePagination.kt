package com.example.appexample

interface OnHandlePagination {
    fun onLoanMore(totalPage: Int, currentPage: Int)
    fun getTotalPage(): Int
}