import { deprecated } from "typesafe-actions";
const { createStandardAction } = deprecated;

// 액션 type
export const GET_CONTAINER = "container/GET_CONTAINER";
export const CREATE_CONTAINER = "container/CREATE_CONTAINER";
export const REMOVE_CONTAINER = "container/REMOVE_CONTAINER";

// 액션 생성 함수
export const getContaier = createStandardAction(GET_CONTAINER)<string>();
export const createContainer = createStandardAction(CREATE_CONTAINER)<number>();
export const removeContainer = createStandardAction(REMOVE_CONTAINER)<number>();
