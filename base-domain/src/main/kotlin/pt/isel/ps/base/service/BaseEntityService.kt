package pt.isel.ps.base.service

import pt.isel.ps.base.model.IdBaseModel
import pt.isel.ps.base.model.PageModel
import java.io.Serializable
import javax.persistence.OrderColumn

interface BaseEntityService<M : IdBaseModel<ID>, ID : Serializable> {
    fun save(model: M) : M
    fun remove(id: ID)
    fun retrieveAll() : List<M>
    fun retrieveAll(offset: Int, pageSize: Int) : List<M>
    fun retrieve(id: ID) : M
    fun getByParams(page: Int, pageSize: Int, orderColumn: String, orderClause: String): PageModel<M>
}