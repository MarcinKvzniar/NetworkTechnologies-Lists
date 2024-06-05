package com.project.networktechproject.controller.user.dto;

import java.util.List;

public class GetUsersPageResponseDto {
    private List<GetUserDto> users;
    private int currentPage;
    private long totalItems;
    private int totalPages;
    private boolean hasMore;

    public GetUsersPageResponseDto(List<GetUserDto> users, int currentPage, long totalItems, int totalPages, boolean hasMore) {
        this.users = users;
        this.currentPage = currentPage;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.hasMore = hasMore;
    }

    public List<GetUserDto> getUsers() {
        return users;
    }

    public void setUsers(List<GetUserDto> users) {
        this.users = users;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }
}
