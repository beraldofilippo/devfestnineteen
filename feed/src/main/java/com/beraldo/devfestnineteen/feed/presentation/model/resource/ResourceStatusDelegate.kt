package com.beraldo.devfestnineteen.feed.presentation.model.resource

abstract class ResourceStatusDelegate<in T> {

    fun handleStatus(resource: Resource<T>?) {
        when (resource?.status) {
            Status.SUCCESS -> {
                handleSuccess(resource)
            }
            Status.ERROR -> {
                handleError(resource)
            }
            Status.LOADING -> {
                handleLoading(resource)
            }
        }
    }

    protected abstract fun handleSuccess(resource: Resource<T>)
    protected abstract fun handleError(resource: Resource<T>)
    protected abstract fun handleLoading(resource: Resource<T>)
}