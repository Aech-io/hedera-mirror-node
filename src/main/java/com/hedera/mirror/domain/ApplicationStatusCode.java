package com.hedera.mirror.domain;

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

public enum ApplicationStatusCode {
    LAST_VALID_DOWNLOADED_RECORD_FILE,
    LAST_VALID_DOWNLOADED_RECORD_FILE_HASH,
    LAST_VALID_DOWNLOADED_BALANCE_FILE,
    LAST_VALID_DOWNLOADED_EVENT_FILE,
    LAST_VALID_DOWNLOADED_EVENT_FILE_HASH,
    EVENT_HASH_MISMATCH_BYPASS_UNTIL_AFTER,
    RECORD_HASH_MISMATCH_BYPASS_UNTIL_AFTER,
    LAST_PROCESSED_EVENT_HASH,
    LAST_PROCESSED_RECORD_HASH
}