package com.springbatch.demo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
//@EnableBatchProcessing
public class BatchConfiguration {

   /*@Bean
	public Tasklet tasklet1() {
		return new Tasklet() {
			@Override
			@Nullable
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("-------------------------->>>>> Styep 1 executed");
				return RepeatStatus.FINISHED;
			}
		};
	}

    @Bean
	public Step step1(JobRepository jobRepository, Tasklet tasklet1, PlatformTransactionManager transactionManager) {
		 return new StepBuilder("step1",jobRepository).tasklet(tasklet1, transactionManager).build();
	}*/

	@Bean
	public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		 return new StepBuilder("step1",jobRepository).tasklet(
		 	(contribution, chunkContext) -> {
			System.out.println("-------------------------->>>>> Styep 1 executed");
		 	return RepeatStatus.FINISHED;
			}, transactionManager).build();
	}

	@Bean
	public Step step2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		 return new StepBuilder("step2",jobRepository).tasklet(
		 	(contribution, chunkContext) -> {
			System.out.println("-------------------------->>>>> Styep 2 executed");
		 	return RepeatStatus.FINISHED;
			}, transactionManager).build();
	}
	
	@Bean
	public Step step3(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		 return new StepBuilder("step3",jobRepository).tasklet(
		 	(contribution, chunkContext) -> {
			System.out.println("-------------------------->>>>> Styep 3 executed");
		 	return RepeatStatus.FINISHED;
			}, transactionManager).build();
	}

    @Bean(name = "firstJob")
	public Job firstJob(JobRepository jobRepository, Step step1, Step step2, Step step3) {
		return new JobBuilder("firstJob", jobRepository).start(step1).next(step2).next(step3).build();
	}

}
