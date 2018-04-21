package com.example.rechee.walmartproducts.dagger.repository;
import dagger.Subcomponent;

/**
 * Created by reche on 1/1/2018.
 */

@RepositoryScope
@Subcomponent(modules={NetModule.class})
public interface RepositoryComponent {
}
