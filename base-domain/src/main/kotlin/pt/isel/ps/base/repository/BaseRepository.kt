package pt.isel.ps.base.repository

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository

interface BaseRepository<E, ID> : JpaRepository<E, ID> {
    fun getByParams(page: Int, pageSize: Int, orderClause: String, orderColumn: String) =
            this.findAll(PageRequest.of(page, pageSize, Sort.Direction.valueOf(orderClause), orderClause))
}