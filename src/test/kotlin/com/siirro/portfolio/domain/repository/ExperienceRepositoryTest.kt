package com.siirro.portfolio.domain.repository

import com.siirro.portfolio.domain.entity.Experience
import com.siirro.portfolio.domain.entity.ExperienceDetail
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS) //메소드들이 전부 의존적인 상황을 원할때, 테스트 단위가 클래스인걸 원할때
class ExperienceRepositoryTest(@Autowired val experienceRepository: ExperienceRepository) {

    var DATA_SIZE = 10
    private fun createExperience(n: Int): Experience {
        val experience = Experience(
            title = "${n}",
            description = "테스트 설명${n}",
            startYear = 2025,
            startMonth = 3,
            endYear = 2025,
            endMonth = 4,
            isActive = true
        )

        val details = mutableListOf<ExperienceDetail>()
        for (i in 1..n) { // 1..10
            val experienceDetail = ExperienceDetail(content = "테스트${i}", isActive = true)
            details.add(experienceDetail)
        }
        experience.addDetails(details)

        println("??? ${experience.details.size}")
        return experience
    }

    @BeforeAll
    fun beforeAll() {
        println("---- 데이터 초기화 이전 조회 시작 ----")
        val beforeInitialize = experienceRepository.findAll()
        assertThat(beforeInitialize).hasSize(0)
        println("---- 데이터 초기화 이전 조회 시작 ----")

        println("---- 테스트 데이터 초기화 시작 ----")
        val experiences = mutableListOf<Experience>()
        for (i in 1..DATA_SIZE) {
            val experience = createExperience(i)
            experiences.add(experience)
        }
        experienceRepository.saveAll(experiences)
        println("---- 테스트 데이터 초기화 종료 ----")
    }

    @Test
    fun testFindAll() {
        println("---- findAll 테스트 시작 ----")
        val experiences = experienceRepository.findAll()
        assertThat(experiences).hasSize(DATA_SIZE)
        println("experiences.size: ${experiences.size}")

        for (experience in experiences) {
            assertThat(experience.details).hasSize(experience.title.toInt())
            println("experience.details.size: ${experience.details.size}")
        }
        println("---- findAll 테스트 종료 ----")
    }

    @Test
    fun testFindAllByIsActive() {
        println("---- findAllByIsActive 테스트 시작 ----")
        val experiences = experienceRepository.findAllByIsActive(false)
        assertThat(experiences).hasSize(DATA_SIZE)
        println("experiences.size: ${experiences.size}")

        for (experience in experiences) {
            assertThat(experience.details).hasSize(experience.title.toInt())
            println("experience.details.size: ${experience.details.size}")
        }
        println("---- findAll 테스트 종료 ----")
    }
}