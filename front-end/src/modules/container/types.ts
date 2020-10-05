import { ActionType } from "typesafe-actions";
import * as actions from "./actions";

export type Container = {
  id: string;
  name: string;
};

export type ContainerAction = ActionType<typeof actions>;

export type ContainerState = Container[];
