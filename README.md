

> **들어가면서..**

이 프로젝트는 Kafka와 Redis의 활용법을 익히고 갑작스럽게 트래픽이 증가하는 경우 대비할 수 있는 로직에 대해서 공부하기 위해서 진행하였습니다.

> **프로젝트의 가정**

Kafka 이벤트 기반 채팅 시스템에서 임계점을 넘는 대규모 FETCH 트래픽이 발생했을 때 redis와 kafka, 스로틀링을 활용하여 트래픽을 잡는다.

> **시스템 아키텍처**

![](https://blog.kakaocdn.net/dn/nzpg8/btsw7wUoWUO/XQczpU5vnGrXaw0capO641/img.png)

LINE의 오픈챗서버 시스템아키텍처

크게 클라이언트(react), ChatServer, PublishServer로 나뉘며 각각 클라이언트는 react를 활용해 채팅방을 구현하였고 ChatServer는 클라이언트에서 메세지나 공감 이벤트가 발생하였을 때 카프카 클러스터로 이벤트를 전달하는 역할을 한다. PublishServer에서는 카프카 클러스터의 이벤트들을 KafkaConsumer를 통하여 Listen하고 SSE (Server Sent Event) 기능을 통하여 클라이언트와 subscribe를 하고 메세지와 공감 이벤트를 실시간으로 갱신시켜준다.

> **프로젝트의 진행 순서**

  
1. 채팅방을 구현하기 위해서 React를 통하여 실시간 채팅방을 구현하였다.  
2. 채팅서버를 구현하기 위해서 message와 reaction을 위한 entity, dto, controller, service, repository를 구성해주었다.  
3. 채팅 서버에서 발생한 이벤트들을 카프카 클러스터에 보내주기 위해서 카프카 서버를 만들어주었다.  
4. 카프카 클러스터에 전달된 이벤트들을 Listen 하기 위해서 PublishServer를 만들어 주었다.  
5.  event가 발생하게 되면 SSE를 통해서 리액트 클라이언트에 이벤트를 실시간으로 알려주는 기능을 만들었다.  
6. 프로젝트의 핵심인 임계점을 넘는 대용량 Fetch 트래픽이 들어오는 경우를 찾기 위해서 Apache Jmeter를 통하여 Timeout이 나거나 request가 실패하는 지점을 찾아주었다. (threads 700, 1 ,loop 10)  
8. 스프링부트 스케줄링을 통하여 1초마다 redis에서 fetch API가 발생한 데이터를 가져오고 임계점을 넘었다면 hotchat 상태를 저장해준다.  
9. 스케줄링에서 만약 임계점을 넘어서지 않았다면 hotchat 상태를 삭제시켜준다.  
9. fetch API를 실행할 때 redis의 현재 상태가 hotchat이라면 스로틀링을 동작시키고 아니라면 정상적으로 API를 동작시킨다.

> **Apache Jmeter Test**

![](https://blog.kakaocdn.net/dn/bUbHt0/btsw2Ksal6U/pN0PpALSsmm6iVEpOShXgK/img.png)

![](https://blog.kakaocdn.net/dn/bHVfEG/btsxh7TgsZ3/KAZUvWAIBwsvavOpkPQj7K/img.png)

> **이벤트 전달을 위해 SSE를 사용한 이유**

![](https://blog.kakaocdn.net/dn/OL2l1/btsxaMCsp7X/u1DKMrZSEYxg0RkPZhgpg1/img.png)

이번 프로젝트에서 React 클라이언트에 Kafka Event 내용을 알려주기 위해서 사용한 방식은 SSE이다. 방식에는 Polling, WebSocket, SSE 등 여러가지 방식이 있지만 이번 프로젝트에서 SSE를 사용한 이유는 다음과 같다. SSE는 서버에서 클라이언트로 데이터를 푸시하는 기술이고 Kafka는 이벤트 스트림을 생성하고 이벤트 발생 시 클라이언트에게 실시간으로 전달할 수 있는 강력한 도구이다. 이렇게 함으로써 클라이언트는 서버로부터 이벤트를 수동으로 Polling하거나 WebSocket을 사용하여 불필요하게 양방향 연결을 유지하고 이벤트를 대기하지 않아도 된다. 또한 브라우저와 서버 간의 통신에 특히 브라우저 호환성이 좋다. 대부분의 최신 브라우저에서 지원되며, 서버 측에서는 일반적인 HTTP 응답을 사용하므로 프록시 서버와 함께 사용하기 쉽다. 

> **스프링부트의 스로틀링 기법에는 여러가지가 있는데 굳이 Redis를 활용한 이유는?**

|   |   |
|---|---|
|![](https://blog.kakaocdn.net/dn/kCedU/btsxkFa58Fy/ISERtEsKukXK3KiZKlAhp0/img.jpg)|![](https://blog.kakaocdn.net/dn/btofZd/btsw8h3IGXN/i9CnKKpXmYxKcy6qZw4Iq1/img.jpg)|

Redis를 사용하지 않고 스로틀링을 구현할 수 있는 자바 라이브러리는 많다. 예를 들어서 Google Guava라이브러리는 그 중 하나이며, 스로틀링을 구현하기에 편리한 도구를 제공한다. 하지만 Redis는 분산 환경에서 높은 확장성을 제공하고 여러 인스턴스를 클러스터로 구성하고 데이터를 분산 저장할 수 있으므로, 대규모 애플리케이션에서도 스로틀링을 효율적으로 관리할 수 있다. 또한 Redis는 스로틀링 이외에도 다양한 데이터 저장 및 관리 요구 사항을 처리할 수 있으며 여러 서버 또는 인스턴스 간에 상태를 공유해야 하는 경우 스로틀링 상태와 같은 정보를 쉽게 공유할 수 있다. 따라서 Redis를 사용하여 스로틀링을 구현할지 여부는 프로젝트의 요구사항과 복잡성에 따라 달라진다. 자바 스로틀링 라이브러리를 사용하면 간단한 스로틀링 요구사항을 빠르게 처리할 수 있으나 애플리케이션이나 서비스가 확장성이 높거나 분산 환경에서 작동해야 하거나 데이터 관리가 복잡한 경우 Redis와 같은 데이터 스토리지를 활용하여 스로틀링을 구현하는 것이 좋을 수 있다. 

> **프로젝트 시연**

![](https://blog.kakaocdn.net/dn/xXXbr/btsxd0ACBl8/D4J7uv9i0Rr18A2KjKHJk0/img.gif)

> **끝으로**..

처음으로 Apache Jmeter를 사용해서 부하테스트를 해보았고 시스템이 견딜 수 있는 트래픽을 측정해보면서 더욱더 대용량 트래픽 처리에 대한 관심을 가지게 되었다. 다음 프로젝트에서는 Jmeter를 통한 테스트도 좋지만 실제 사용자가 존재하는 애플리케이션을 구축하여서 트래픽을 측정하고 유지보수 할 수 있는 경험을 꼭 해보고 싶다는 생각을 가지게 되었다. 
