package study.rationalegoism.mvp_rx_study.data;

import dagger.Component;
import study.rationalegoism.mvp_rx_study.data.repository.RandomUsersRepository;

@Component
public interface RepositoryComponent {
    RandomUsersRepository provideRandomUsersRepository();
}
