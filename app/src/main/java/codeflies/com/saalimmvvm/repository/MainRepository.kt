package codeflies.com.saalimmvvm.repository

import codeflies.com.saalimmvvm.model.PostResultItem
import codeflies.com.saalimmvvm.model.responseClass.home.HomeResponse
import codeflies.com.saalimmvvm.model.responseClass.maintenance.MaintenanceResponse
import codeflies.com.saalimmvvm.network.ApiService

class MainRepository(private val apiService: ApiService) {

    suspend fun getpost(): List<PostResultItem> {
        return apiService.getPost()
    }

    suspend fun getHomePosts(): HomeResponse {
        return apiService.getPostHome("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY1YzYwOWQ3NTllODhhMmUyNDA3NDZhOCIsImlhdCI6MTcwOTg5NjIyNH0.cqpfxvZcHVLGsrJQRVxj6BD0VNgVWJXyh2_D3-eyqEY")
    }

    suspend fun getMaintenancePosts(): MaintenanceResponse {
        return apiService.getPostMaintain("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY1YzYwOWQ3NTllODhhMmUyNDA3NDZhOCIsImlhdCI6MTcwOTg5NjIyNH0.cqpfxvZcHVLGsrJQRVxj6BD0VNgVWJXyh2_D3-eyqEY")
    }
}
