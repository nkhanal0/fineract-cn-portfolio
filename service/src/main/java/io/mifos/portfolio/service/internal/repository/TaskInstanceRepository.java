/*
 * Copyright 2017 The Mifos Initiative.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.mifos.portfolio.service.internal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Myrle Krantz
 */
@Repository
public interface TaskInstanceRepository extends JpaRepository<TaskInstanceEntity, Long> {
  @SuppressWarnings("JpaQlInspection")
  @Query("SELECT t FROM TaskInstanceEntity t WHERE t.taskDefinition.product.identifier = :productIdentifier AND t.customerCase.identifier = :caseIdentifier")
  Stream<TaskInstanceEntity> findByProductIdAndCaseId(@Param("productIdentifier") String productId, @Param("caseIdentifier") String caseId);

  @SuppressWarnings("JpaQlInspection")
  @Query("SELECT t FROM TaskInstanceEntity t WHERE t.taskDefinition.product.identifier = :productIdentifier AND t.customerCase.identifier = :caseIdentifier AND t.executedOn = NULL")
  Stream<TaskInstanceEntity> findByProductIdAndCaseIdAndExcludeExecuted(@Param("productIdentifier") String productId, @Param("caseIdentifier") String caseId);

  @SuppressWarnings("JpaQlInspection")
  @Query("SELECT t FROM TaskInstanceEntity t WHERE t.taskDefinition.product.identifier = :productIdentifier AND t.customerCase.identifier = :caseIdentifier AND t.taskDefinition.identifier = :taskIdentifier")
  Optional<TaskInstanceEntity> findByProductIdAndCaseIdAndTaskId(@Param("productIdentifier") String productId, @Param("caseIdentifier") String caseId, @Param("taskIdentifier") String taskId);
}
