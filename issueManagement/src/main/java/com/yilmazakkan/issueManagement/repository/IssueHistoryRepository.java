package com.yilmazakkan.issueManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yilmazakkan.issueManagement.entity.IssueHistory;

public interface IssueHistoryRepository extends JpaRepository<IssueHistory, Long>{

}
