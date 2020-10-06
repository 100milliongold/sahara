import { createAsyncAction } from "typesafe-actions";
import { ContainerType } from "../../api/container";
import { AxiosError } from "axios";

export const GET_CONTAINER_LIST = "container/GET_CONTAINER_LIST";
export const GET_CONTAINER_LIST_SUCCESS =
  "container/GET_CONTAINER_LIST_SUCCESS";
export const GET_CONTAINER_LIST_ERROR = "container/GET_CONTAINER_LIST_ERROR";

export const getContainerListAsync = createAsyncAction(
  GET_CONTAINER_LIST,
  GET_CONTAINER_LIST_SUCCESS,
  GET_CONTAINER_LIST_ERROR
)<string, ContainerType, AxiosError>();
