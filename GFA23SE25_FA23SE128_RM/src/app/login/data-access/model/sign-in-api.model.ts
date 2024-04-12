import { FormControl } from "@angular/forms";

export namespace SignInApi {
  export interface Request {
    value: string;
    password: string
  }

  export type RequestFormGroup = {
    value: FormControl<string>;
    password: FormControl<string>;
  }
}
