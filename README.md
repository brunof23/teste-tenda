# teste-tenda


Arquiteura do projeto:

coupon-api
│
├── domain
│   ├── model/Coupon.java
│   ├── exception/DomainException.java
│   ├── exception/CouponAlreadyDeletedException.java
│   └── repository/CouponRepository.java
│
├── application
│   ├── command/CreateCouponCommand.java
│   ├── usecase/CreateCouponUseCase.java
│   └── usecase/DeleteCouponUseCase.java
│
├── infrastructure
│   └── persistence
│       ├── CouponEntity.java
│       ├── CouponMapper.java
│       ├── SpringCouponJpaRepository.java
│       └── CouponRepositoryJpa.java
│
├── interfaces
│   └── web
│       ├── dto/CreateCouponRequest.java
│       └── CouponController.java
│
├── CouponApplication.java
└── resources/application.yml
