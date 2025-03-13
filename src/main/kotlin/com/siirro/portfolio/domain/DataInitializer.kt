package com.siirro.portfolio.domain

import com.siirro.portfolio.domain.constant.SkillType
import com.siirro.portfolio.domain.entity.*
import com.siirro.portfolio.domain.repository.*
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
// 프로필이 이 value일때만 이 클래스 생성해서 빈으로 등록해라.
@Profile(value = ["default"])
class DataInitializer(
    private val achievementRepository: AchievementRepository,
    private val introductionRepository: IntroductionRepository,
    private val linkRepository: LinkRepository,
    private val skillRepository: SkillRepository,
    private val projectRepository: ProjectRepository,
    private val experienceRepository: ExperienceRepository
) {

    @PostConstruct
    fun initializeData() {
        println("스프링이 실행되었습니다. 테스트 데이터를 초기화합니다.")


        val achievements = mutableListOf<Achievement>(
            Achievement(
                title = "2025 Kakao 해커톤 최우수상",
                description = "쇼핑몰 검색 서비스의 아키텍처, 데이터 모델링",
                host = "카카오",
                achievedDate = LocalDate.of(2025, 2, 25),
                isActive = true
            ),
            Achievement(
                title = "정보처리기사",
                description = "자료구조, 운영체제, 알고리즘, 데이터베이스 등",
                host = "한국산업인력공단",
                achievedDate = LocalDate.of(2026, 5, 25),
                isActive = true
            )
        )
        achievementRepository.saveAll(achievements)

        val introductions = mutableListOf<Introduction>(
            Introduction(content = "코드 한 줄의 위대함을 아는 개발하는 강아지 코코입니다", isActive = true),
            Introduction(content = "코드를 위한 코드가 아닌, 비즈니스를 위한 코드를 추구합니다", isActive = true),
            Introduction(content = "꺼져가는 코드도 다시 리팩토링하자", isActive = true),
        )
        introductionRepository.saveAll(introductions)

        val links = mutableListOf<Link>(
            Link(name = "Github", content = "http://github.com", isActive = true),
            Link(name = "Linkedin", content = "http://www.linkedin.com/in", isActive = true)
        )
        linkRepository.saveAll(links)

        val experience1 = Experience(
            title = "한국방송통신대학교",
            description = "컴퓨터과학 전공",
            startYear = 2025,
            startMonth = 3,
            endYear = 2027,
            endMonth = 2,
            isActive = true
        )
        experience1.addDetails(
            mutableListOf(
                ExperienceDetail(content = "GPA 4.3/4.5", isActive = true),
                ExperienceDetail(content = "소프트웨어 개발 동아리 활동", isActive = false)
            )
        )
        val experience2 = Experience(
            title = "신진아이티컨설팅",
            description = "SI, SM 경력 다수",
            startYear = 2024,
            startMonth = 10,
            endYear = 2027,
            endMonth = 6,
            isActive = true
        )
        experience2.addDetails(
            mutableListOf(
                ExperienceDetail(content = "한국투자무역진흥공사 전사적자원관리시스템 개발", isActive = true),
                ExperienceDetail(content = "중소기업지원재단 인사관리시스템 개발, 유지보수", isActive = true)
            )
        )
        experienceRepository.saveAll(mutableListOf(experience1, experience2))

        val java = Skill(name = "Java", type = SkillType.LANGUAGE.name, isActive = true)
        val kotlin = Skill(name = "Java", type = SkillType.LANGUAGE.name, isActive = true)
        val spring = Skill(name = "Java", type = SkillType.FRAMEWORK.name, isActive = true)
        val mysql = Skill(name = "Java", type = SkillType.DATABASE.name, isActive = true)
        skillRepository.saveAll(mutableListOf(java, kotlin, spring, mysql))

        val project1 = Project(
            name = "프로젝트1",
            description = "학사시스템",
            startYear = 2025,
            startMonth = 5,
            endYear = 2025,
            endMonth = 7,
            isActive = true
        )
        project1.addDetails(
            mutableListOf(
                ProjectDetail(content = "설명1", url = null, isActive = false),
                ProjectDetail(content = "설명12", url = null, isActive = false)
            )
        )
        project1.skills.addAll(
            mutableListOf(
                ProjectSkill(project = project1, skill = java),
                ProjectSkill(project = project1, skill = spring),
                ProjectSkill(project = project1, skill = mysql)
            )
        )

        val project2 = Project(
            name = "프로젝트2",
            description = "카페주문",
            startYear = 2025,
            startMonth = 10,
            endYear = 2025,
            endMonth = 12,
            isActive = false
        )
        project2.addDetails(
            mutableListOf(
                ProjectDetail(content = "설명1", url = null, isActive = false),
                ProjectDetail(content = "설명2", url = null, isActive = false),
                ProjectDetail(content = "설명3", url = "https://github.com", isActive = false)
            )
        )
        project2.skills.addAll(
            mutableListOf(
                ProjectSkill(project = project2, skill = kotlin),
                ProjectSkill(project = project2, skill = spring),
                ProjectSkill(project = project2, skill = mysql)
            )
        )
        projectRepository.saveAll(mutableListOf(project1, project2))
    }
}