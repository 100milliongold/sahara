import { Container, ContainerAction, ContainerState } from "./types";
import { getContainerListAsync } from "./actions";
import { createReducer } from "typesafe-actions";
import {
  GET_CONTAINER_LIST,
  GET_CONTAINER_LIST_SUCCESS,
  GET_CONTAINER_LIST_ERROR,
} from "./actions";
import {
  asyncState,
  createAsyncReducer,
  transformToArray,
} from "../../lib/reducerUtils";

const initialState: ContainerState = {
  containerList: asyncState.initial(),
};

const containers = createReducer<ContainerState, ContainerAction>(
  initialState
).handleAction(
  transformToArray(getContainerListAsync),
  createAsyncReducer(getContainerListAsync, "containerList")
);

export default containers;
