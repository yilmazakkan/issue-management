package com.yilmazakkan.issueManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yilmazakkan.issueManagement.entity.Issue;

public interface IssueRepository extends JpaRepository<Issue, Long>{


}
