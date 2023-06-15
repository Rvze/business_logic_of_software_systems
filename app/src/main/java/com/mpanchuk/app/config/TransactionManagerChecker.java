package com.mpanchuk.app.config;

import com.atomikos.icatch.jta.UserTransactionManager;
import jakarta.transaction.TransactionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

@Component
@RequiredArgsConstructor
public class TransactionManagerChecker {

    private final PlatformTransactionManager transactionManager;

    public String getTransactionManagerType() {
        if (transactionManager instanceof JtaTransactionManager) {
            TransactionManager underlyingTransactionManager = ((JtaTransactionManager) transactionManager)
                    .getTransactionManager();
            if (underlyingTransactionManager instanceof UserTransactionManager) {
                return "Атомикос";
            }
            // Если вы используете другой JTA-драйвер, вы можете добавить соответствующую проверку здесь
        }
        return "Неизвестно";
    }
}