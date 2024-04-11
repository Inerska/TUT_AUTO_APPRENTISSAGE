import org.arobase.domain.model.request.LanguageCreateRequest;
import org.arobase.infrastructure.persistence.entity.Language;
import org.arobase.infrastructure.persistence.repository.LanguageRepository;
import org.arobase.infrastructure.persistence.service.LanguageService;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class LanguageServiceTest {

    @Mock
    private LanguageRepository languageRepository;

    @Mock
    private Logger logger;

    @InjectMocks
    private LanguageService languageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updateLanguage_InvalidId_ThrowsException() {
        String id = "invalidId";
        LanguageCreateRequest request = new LanguageCreateRequest("Java", "J");

        assertThrows(IllegalArgumentException.class, () -> languageService.updateLanguage(id, request));
        verify(languageRepository, never()).findByIdOptional(anyString());
        verify(languageRepository, never()).persistOrUpdate(any(Language.class));
    }

    @Test
    void findAllLanguages_ReturnsAllLanguages() {
        languageService.findAllLanguages();

        verify(languageRepository, times(1)).listAll();
    }
}
