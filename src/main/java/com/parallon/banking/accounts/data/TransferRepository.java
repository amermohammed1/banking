package com.parallon.banking.accounts.data;

import com.parallon.banking.accounts.data.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {


}