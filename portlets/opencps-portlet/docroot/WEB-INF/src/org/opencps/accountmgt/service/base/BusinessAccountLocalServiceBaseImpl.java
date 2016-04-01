
/*******************************************************************************
 * OpenCPS is the open source Core Public Services software
 * Copyright (C) 2016-present OpenCPS community
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/


package org.opencps.accountmgt.service.base;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.service.persistence.UserPersistence;

import org.opencps.accountmgt.model.BusinessAccount;
import org.opencps.accountmgt.service.BusinessAccountLocalService;
import org.opencps.accountmgt.service.persistence.BusinessAccountPersistence;
import org.opencps.accountmgt.service.persistence.BusinessDomainPersistence;
import org.opencps.accountmgt.service.persistence.BusinessPersistence;
import org.opencps.accountmgt.service.persistence.CitizenPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the business account local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link org.opencps.accountmgt.service.impl.BusinessAccountLocalServiceImpl}.
 * </p>
 *
 * @author khoavd
 * @see org.opencps.accountmgt.service.impl.BusinessAccountLocalServiceImpl
 * @see org.opencps.accountmgt.service.BusinessAccountLocalServiceUtil
 * @generated
 */
public abstract class BusinessAccountLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements BusinessAccountLocalService,
		IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link org.opencps.accountmgt.service.BusinessAccountLocalServiceUtil} to access the business account local service.
	 */

	/**
	 * Adds the business account to the database. Also notifies the appropriate model listeners.
	 *
	 * @param businessAccount the business account
	 * @return the business account that was added
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public BusinessAccount addBusinessAccount(BusinessAccount businessAccount)
		throws SystemException {
		businessAccount.setNew(true);

		return businessAccountPersistence.update(businessAccount);
	}

	/**
	 * Creates a new business account with the primary key. Does not add the business account to the database.
	 *
	 * @param businessAccountId the primary key for the new business account
	 * @return the new business account
	 */
	@Override
	public BusinessAccount createBusinessAccount(long businessAccountId) {
		return businessAccountPersistence.create(businessAccountId);
	}

	/**
	 * Deletes the business account with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param businessAccountId the primary key of the business account
	 * @return the business account that was removed
	 * @throws PortalException if a business account with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public BusinessAccount deleteBusinessAccount(long businessAccountId)
		throws PortalException, SystemException {
		return businessAccountPersistence.remove(businessAccountId);
	}

	/**
	 * Deletes the business account from the database. Also notifies the appropriate model listeners.
	 *
	 * @param businessAccount the business account
	 * @return the business account that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public BusinessAccount deleteBusinessAccount(
		BusinessAccount businessAccount) throws SystemException {
		return businessAccountPersistence.remove(businessAccount);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(BusinessAccount.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return businessAccountPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.accountmgt.model.impl.BusinessAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return businessAccountPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.accountmgt.model.impl.BusinessAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return businessAccountPersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return businessAccountPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) throws SystemException {
		return businessAccountPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public BusinessAccount fetchBusinessAccount(long businessAccountId)
		throws SystemException {
		return businessAccountPersistence.fetchByPrimaryKey(businessAccountId);
	}

	/**
	 * Returns the business account with the primary key.
	 *
	 * @param businessAccountId the primary key of the business account
	 * @return the business account
	 * @throws PortalException if a business account with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BusinessAccount getBusinessAccount(long businessAccountId)
		throws PortalException, SystemException {
		return businessAccountPersistence.findByPrimaryKey(businessAccountId);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException, SystemException {
		return businessAccountPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the business accounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.accountmgt.model.impl.BusinessAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of business accounts
	 * @param end the upper bound of the range of business accounts (not inclusive)
	 * @return the range of business accounts
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BusinessAccount> getBusinessAccounts(int start, int end)
		throws SystemException {
		return businessAccountPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of business accounts.
	 *
	 * @return the number of business accounts
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getBusinessAccountsCount() throws SystemException {
		return businessAccountPersistence.countAll();
	}

	/**
	 * Updates the business account in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param businessAccount the business account
	 * @return the business account that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public BusinessAccount updateBusinessAccount(
		BusinessAccount businessAccount) throws SystemException {
		return businessAccountPersistence.update(businessAccount);
	}

	/**
	 * Returns the business local service.
	 *
	 * @return the business local service
	 */
	public org.opencps.accountmgt.service.BusinessLocalService getBusinessLocalService() {
		return businessLocalService;
	}

	/**
	 * Sets the business local service.
	 *
	 * @param businessLocalService the business local service
	 */
	public void setBusinessLocalService(
		org.opencps.accountmgt.service.BusinessLocalService businessLocalService) {
		this.businessLocalService = businessLocalService;
	}

	/**
	 * Returns the business remote service.
	 *
	 * @return the business remote service
	 */
	public org.opencps.accountmgt.service.BusinessService getBusinessService() {
		return businessService;
	}

	/**
	 * Sets the business remote service.
	 *
	 * @param businessService the business remote service
	 */
	public void setBusinessService(
		org.opencps.accountmgt.service.BusinessService businessService) {
		this.businessService = businessService;
	}

	/**
	 * Returns the business persistence.
	 *
	 * @return the business persistence
	 */
	public BusinessPersistence getBusinessPersistence() {
		return businessPersistence;
	}

	/**
	 * Sets the business persistence.
	 *
	 * @param businessPersistence the business persistence
	 */
	public void setBusinessPersistence(BusinessPersistence businessPersistence) {
		this.businessPersistence = businessPersistence;
	}

	/**
	 * Returns the business account local service.
	 *
	 * @return the business account local service
	 */
	public org.opencps.accountmgt.service.BusinessAccountLocalService getBusinessAccountLocalService() {
		return businessAccountLocalService;
	}

	/**
	 * Sets the business account local service.
	 *
	 * @param businessAccountLocalService the business account local service
	 */
	public void setBusinessAccountLocalService(
		org.opencps.accountmgt.service.BusinessAccountLocalService businessAccountLocalService) {
		this.businessAccountLocalService = businessAccountLocalService;
	}

	/**
	 * Returns the business account remote service.
	 *
	 * @return the business account remote service
	 */
	public org.opencps.accountmgt.service.BusinessAccountService getBusinessAccountService() {
		return businessAccountService;
	}

	/**
	 * Sets the business account remote service.
	 *
	 * @param businessAccountService the business account remote service
	 */
	public void setBusinessAccountService(
		org.opencps.accountmgt.service.BusinessAccountService businessAccountService) {
		this.businessAccountService = businessAccountService;
	}

	/**
	 * Returns the business account persistence.
	 *
	 * @return the business account persistence
	 */
	public BusinessAccountPersistence getBusinessAccountPersistence() {
		return businessAccountPersistence;
	}

	/**
	 * Sets the business account persistence.
	 *
	 * @param businessAccountPersistence the business account persistence
	 */
	public void setBusinessAccountPersistence(
		BusinessAccountPersistence businessAccountPersistence) {
		this.businessAccountPersistence = businessAccountPersistence;
	}

	/**
	 * Returns the business domain local service.
	 *
	 * @return the business domain local service
	 */
	public org.opencps.accountmgt.service.BusinessDomainLocalService getBusinessDomainLocalService() {
		return businessDomainLocalService;
	}

	/**
	 * Sets the business domain local service.
	 *
	 * @param businessDomainLocalService the business domain local service
	 */
	public void setBusinessDomainLocalService(
		org.opencps.accountmgt.service.BusinessDomainLocalService businessDomainLocalService) {
		this.businessDomainLocalService = businessDomainLocalService;
	}

	/**
	 * Returns the business domain remote service.
	 *
	 * @return the business domain remote service
	 */
	public org.opencps.accountmgt.service.BusinessDomainService getBusinessDomainService() {
		return businessDomainService;
	}

	/**
	 * Sets the business domain remote service.
	 *
	 * @param businessDomainService the business domain remote service
	 */
	public void setBusinessDomainService(
		org.opencps.accountmgt.service.BusinessDomainService businessDomainService) {
		this.businessDomainService = businessDomainService;
	}

	/**
	 * Returns the business domain persistence.
	 *
	 * @return the business domain persistence
	 */
	public BusinessDomainPersistence getBusinessDomainPersistence() {
		return businessDomainPersistence;
	}

	/**
	 * Sets the business domain persistence.
	 *
	 * @param businessDomainPersistence the business domain persistence
	 */
	public void setBusinessDomainPersistence(
		BusinessDomainPersistence businessDomainPersistence) {
		this.businessDomainPersistence = businessDomainPersistence;
	}

	/**
	 * Returns the citizen local service.
	 *
	 * @return the citizen local service
	 */
	public org.opencps.accountmgt.service.CitizenLocalService getCitizenLocalService() {
		return citizenLocalService;
	}

	/**
	 * Sets the citizen local service.
	 *
	 * @param citizenLocalService the citizen local service
	 */
	public void setCitizenLocalService(
		org.opencps.accountmgt.service.CitizenLocalService citizenLocalService) {
		this.citizenLocalService = citizenLocalService;
	}

	/**
	 * Returns the citizen remote service.
	 *
	 * @return the citizen remote service
	 */
	public org.opencps.accountmgt.service.CitizenService getCitizenService() {
		return citizenService;
	}

	/**
	 * Sets the citizen remote service.
	 *
	 * @param citizenService the citizen remote service
	 */
	public void setCitizenService(
		org.opencps.accountmgt.service.CitizenService citizenService) {
		this.citizenService = citizenService;
	}

	/**
	 * Returns the citizen persistence.
	 *
	 * @return the citizen persistence
	 */
	public CitizenPersistence getCitizenPersistence() {
		return citizenPersistence;
	}

	/**
	 * Sets the citizen persistence.
	 *
	 * @param citizenPersistence the citizen persistence
	 */
	public void setCitizenPersistence(CitizenPersistence citizenPersistence) {
		this.citizenPersistence = citizenPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.service.UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		Class<?> clazz = getClass();

		_classLoader = clazz.getClassLoader();

		PersistedModelLocalServiceRegistryUtil.register("org.opencps.accountmgt.model.BusinessAccount",
			businessAccountLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"org.opencps.accountmgt.model.BusinessAccount");
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	@Override
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	@Override
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	@Override
	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		if (contextClassLoader != _classLoader) {
			currentThread.setContextClassLoader(_classLoader);
		}

		try {
			return _clpInvoker.invokeMethod(name, parameterTypes, arguments);
		}
		finally {
			if (contextClassLoader != _classLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	protected Class<?> getModelClass() {
		return BusinessAccount.class;
	}

	protected String getModelClassName() {
		return BusinessAccount.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = businessAccountPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = org.opencps.accountmgt.service.BusinessLocalService.class)
	protected org.opencps.accountmgt.service.BusinessLocalService businessLocalService;
	@BeanReference(type = org.opencps.accountmgt.service.BusinessService.class)
	protected org.opencps.accountmgt.service.BusinessService businessService;
	@BeanReference(type = BusinessPersistence.class)
	protected BusinessPersistence businessPersistence;
	@BeanReference(type = org.opencps.accountmgt.service.BusinessAccountLocalService.class)
	protected org.opencps.accountmgt.service.BusinessAccountLocalService businessAccountLocalService;
	@BeanReference(type = org.opencps.accountmgt.service.BusinessAccountService.class)
	protected org.opencps.accountmgt.service.BusinessAccountService businessAccountService;
	@BeanReference(type = BusinessAccountPersistence.class)
	protected BusinessAccountPersistence businessAccountPersistence;
	@BeanReference(type = org.opencps.accountmgt.service.BusinessDomainLocalService.class)
	protected org.opencps.accountmgt.service.BusinessDomainLocalService businessDomainLocalService;
	@BeanReference(type = org.opencps.accountmgt.service.BusinessDomainService.class)
	protected org.opencps.accountmgt.service.BusinessDomainService businessDomainService;
	@BeanReference(type = BusinessDomainPersistence.class)
	protected BusinessDomainPersistence businessDomainPersistence;
	@BeanReference(type = org.opencps.accountmgt.service.CitizenLocalService.class)
	protected org.opencps.accountmgt.service.CitizenLocalService citizenLocalService;
	@BeanReference(type = org.opencps.accountmgt.service.CitizenService.class)
	protected org.opencps.accountmgt.service.CitizenService citizenService;
	@BeanReference(type = CitizenPersistence.class)
	protected CitizenPersistence citizenPersistence;
	@BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
	protected com.liferay.counter.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.service.ResourceLocalService.class)
	protected com.liferay.portal.service.ResourceLocalService resourceLocalService;
	@BeanReference(type = com.liferay.portal.service.UserLocalService.class)
	protected com.liferay.portal.service.UserLocalService userLocalService;
	@BeanReference(type = com.liferay.portal.service.UserService.class)
	protected com.liferay.portal.service.UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private String _beanIdentifier;
	private ClassLoader _classLoader;
	private BusinessAccountLocalServiceClpInvoker _clpInvoker = new BusinessAccountLocalServiceClpInvoker();
}