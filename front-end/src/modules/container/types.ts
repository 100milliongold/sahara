import { ActionType } from "typesafe-actions";
import { ContainerType } from "../../api/container";
import { AsyncState } from "../../lib/reducerUtils";

import * as actions from "./actions";

export type Container = {
  id: string;
  name: string;
};

export type ContainerAction = ActionType<typeof actions>;

export type ContainerState = {
  containerList: AsyncState<ContainerType, Error>;
};
