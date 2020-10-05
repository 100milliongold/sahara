import axios from "axios";

export async function getContainerList() {
  // Generic 을 통해 응답 데이터의 타입을 설정 할 수 있습니다.
  const response = await axios.get<DockerContainer>(
    `https://localhost:8080/api/v1/docker/container`
  );
  return response.data; // 데이터 값을 바로 반환하도록 처리합니다.
}

export interface DockerContainer {
  contaier_id: string;
  container_name: string;
  image_id: string;
  image_name: string;
}
