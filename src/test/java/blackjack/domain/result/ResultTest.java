package blackjack.domain.result;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ResultTest {

    @ParameterizedTest
    @CsvSource(value = {"19:18:WIN", "18:19:LOSE", "18:18:TIE", "23:18:LOSE", "23:22:TIE", "14:22:WIN"}, delimiter = ':')
    @DisplayName("calculateResult()는 플레이어의 결과를 반환한다.")
    void calculate_result(String playerScore, String dealerScore, String inputResult) {
        // given & when
        Result result = Result.calculateResult(Integer.parseInt(playerScore), Integer.parseInt(dealerScore));
        Result expectedResult = Result.valueOf(inputResult);

        // then
        Assertions.assertThat(result).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource(value = {"LOSE:WIN", "WIN:LOSE", "TIE:TIE"}, delimiter = ':')
    @DisplayName("ofOppositeResult()는 특정 게임 결과를 입력하면 상대방의 결과를 반환한다.")
    void test_2(String inputPlayerResult, String inputDealerResult) {
        // given & when
        Result playerResult = Result.valueOf(inputPlayerResult);
        Result expectedResult = Result.valueOf(inputDealerResult);

        // then
        Assertions.assertThat(playerResult.ofOppositeResult()).isEqualTo(expectedResult);
    }
}
