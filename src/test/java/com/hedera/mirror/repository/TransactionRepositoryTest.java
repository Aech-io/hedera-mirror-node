package com.hedera.mirror.repository;

/*-
 * ‌
 * Hedera Mirror Node
 * ​
 * Copyright (C) 2019 Hedera Hashgraph, LLC
 * ​
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ‍
 */

import com.hedera.mirror.domain.Transaction;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@Sql(executionPhase= Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts="classpath:db/scripts/cleanup.sql") // Class manually commits so have to manually cleanup tables
public class TransactionRepositoryTest extends AbstractRepositoryTest {

    @Test
    void insert() {
		Transaction transaction = transactionRepository.save(transaction());
		assertThat(transactionRepository.findById(transaction.getConsensusNs()).get())
    		.isNotNull()
    		.isEqualTo(transaction);
    }

    @Test
	void existsByEntityAndType() {
		Transaction transaction = transactionRepository.save(transaction());
		assertThat(transactionRepository.existsByEntityAndType(transaction.getEntityId(), "CRYPTOCREATEACCOUNT", "FILECREATE")).isEqualTo(true);
		assertThat(transactionRepository.existsByEntityAndType(transaction.getEntityId(), "CONTRACTCREATEINSTANCE")).isEqualTo(false);
		assertThat(transactionRepository.existsByEntityAndType(-1L, "CRYPTOCREATEACCOUNT")).isEqualTo(false);
	}

	private Transaction transaction() {
		Long recordFileId = insertRecordFile().getId();
		Long txEntityId = insertAccountEntity().getId();
		Long nodeAccountId = insertAccountEntity().getId();
		Long payerAccountId = insertAccountEntity().getId();
		Integer transactionTypeId = transactionTypeRepository.findByName("CRYPTOCREATEACCOUNT").get().getId();
		Integer resultId = transactionResultRepository.findByResult("SUCCESS").get().getId();

		Transaction transaction = new Transaction();
		transaction.setChargedTxFee(100L);
		transaction.setConsensusNs(10L);
		transaction.setEntityId(txEntityId);
		transaction.setInitialBalance(1000L);
		transaction.setMemo("transaction memo".getBytes());
		transaction.setNodeAccountId(nodeAccountId);
		transaction.setPayerAccountId(payerAccountId);
		transaction.setRecordFileId(recordFileId);
		transaction.setResultId(resultId);
		transaction.setTransactionTypeId(transactionTypeId);
		transaction.setValidStartNs(20L);
		transaction.setValidDurationSeconds(11L);
		transaction.setMaxFee(33L);
		return transaction;
	}
}
