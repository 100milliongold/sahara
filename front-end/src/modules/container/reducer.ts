import { Container, ContainerAction, ContainerState } from "./types";
import { createReducer } from "typesafe-actions";
import { GET_CONTAINER, CREATE_CONTAINER, REMOVE_CONTAINER } from "./actions";

const initialState: ContainerState = [];

const containers = createReducer<ContainerState, ContainerAction>(
  initialState,
  {
    [GET_CONTAINER]: (state) => [],
  }
);

export default containers;
