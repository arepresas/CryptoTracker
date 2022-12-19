package stream.arepresas.cryptotracker.features.cryptos.tasks;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import stream.arepresas.cryptotracker.features.cryptos.CryptoApiEndpoints;

import static java.time.Instant.now;

@RequiredArgsConstructor
@RestController
@Tag(name = "CryptoTasks")
@RequestMapping(value = CryptoApiEndpoints.CRYPTO_TASK_BASE_URL)
public class CryptoTaskController {

  private final TaskScheduler taskScheduler;
  private final UpdateCryptosTask updateCryptosTask;

  @GetMapping(value = CryptoApiEndpoints.CRYPTO_TASK_LAST_PRICES)
  @ResponseStatus(HttpStatus.OK)
  public void cryptoInfoAndPriceUpdateTask() {
    taskScheduler.schedule(updateCryptosTask, now());
  }
}
