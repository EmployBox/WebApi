package pt.isel.ps.employbox.service

import pt.isel.ps.base.MapperFactoryBean
import pt.isel.ps.employbox.business.CompanyBO
import pt.isel.ps.employbox.domain.Company
import pt.isel.ps.employbox.model.CompanyModel
import pt.isel.ps.employbox.model.PageModel
import org.springframework.stereotype.Component

/**
 * @author tiago.ribeiro
 */
@Component
class CompanyServiceImpl(
        mapperFactoryBean: MapperFactoryBean,
        override val business: CompanyBO
) : AbstractBaseEntityService<Company, CompanyModel, Long>(mapperFactoryBean), CompanyService {

    override val modelClass: Class<CompanyModel> = CompanyModel::class.java

    override val entityClass: Class<Company> = Company::class.java

    override fun convertBeforeSave(model: CompanyModel) = convert(model, entityClass)

    override fun findByParams(ratingLow: Int, ratingHigh: Int, name: String, specialization: String, yearFounded: Int, address: String, orderColumn: String, orderClause: String, page: Int, pageSize: Int): PageModel<CompanyModel> {
        return convert(business.findByParams(ratingLow, ratingHigh, name, specialization, yearFounded, orderColumn, orderClause, page, pageSize), PageModel::class.java)
                .map { convert(it, modelClass) }
    }
}