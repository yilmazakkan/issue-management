package com.yilmazakkan.issueManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yilmazakkan.issueManagement.entity.IssueHistory;

public interface IssueHistoryRepository extends JpaRepository<IssueHistory, Long>{

	List<IssueHistory> getByIssueIdOrderById(Long id);
}
