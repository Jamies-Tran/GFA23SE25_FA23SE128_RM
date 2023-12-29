import { FormControl, FormGroup } from '@angular/forms';

export namespace CategoryAddApi {
  export interface Request {
    description: string;
    title: string;
  }

  export type RequestFormGroup = {
    description: FormControl<string>;
    title: FormControl<string>;
  };

  export function mapModel(frm: FormGroup<RequestFormGroup>): Request {
    const formValue = frm.getRawValue();
    return {
      description: formValue.description,
      title: formValue.title,
    };
  }
}

export namespace CategoryDataGet {
  export interface Response {
    values: {
      categoryId: number;
      name: string | null;
      categoryType: string;
      serviceList: string | null;
    }[];
  }
}

export namespace ServiceAddApi {
  export interface Request {
    name: string;
    description: string;
    categoryId: number;
    serviceDisplayList: serviceDisplayList;
    duration: number;
  }

  export type serviceDisplayList = {
    serviceDisplayUrl: string;
  }[];

  export type RequestFormGroup = {
    description: FormControl<string>;
    name: FormControl<string>;
    categoryId: FormControl<number | null>;
    duration: FormControl<number>;
    serviceDisplayList: FormControl<serviceDisplayList>;
  };

  export function mapModel(frm: FormGroup<RequestFormGroup>): Request {
    const formValue = frm.getRawValue();
    return {
      description: formValue.description,
      categoryId: formValue.categoryId!,
      duration: formValue.duration,
      name: formValue.name,
      serviceDisplayList: formValue.serviceDisplayList,
    };
  }
}

export namespace ServicePagingApi {
  export interface Request {
    search: string;
    current: number;
    sorter: string;
    pageSize: number;
    orderDescending: boolean;
  }

  export interface Response {
    description: string;
    name: string;
    branchServiceList: branchServiceList;
    serviceId: number;
  }

  export type branchServiceList = {
    serviceId: number;
    branchId: number;
    serviceName: string;
    branchName: string;
    thumbnailUrl: string;
    price: number;
  }[];
}

export namespace ServiceDataApi {
  export interface Response {
    values: {
      name: string;
      serviceId: number;
    }[];
  }
}

export namespace ServiceUpdateApi {
  export interface Request {
    name: string;
    description: string;
    categoryId: number;
    serviceDisplayList: serviceDisplayList;
    duration: number;
  }

  export type serviceDisplayList = {
    serviceDisplayUrl: string;
  }[];

  export type RequestFormGroup = {
    description: FormControl<string>;
    name: FormControl<string>;
    categoryId: FormControl<number | null>;
    duration: FormControl<number>;
    serviceDisplayList: FormControl<serviceDisplayList>;
  };

  export function mapModel(frm: FormGroup<RequestFormGroup>): Request {
    const formValue = frm.getRawValue();
    return {
      description: formValue.description,
      categoryId: formValue.categoryId!,
      duration: formValue.duration,
      name: formValue.name,
      serviceDisplayList: formValue.serviceDisplayList,
    };
  }
}

export namespace ServiceGetApi {
  export interface Response {
    value: {
      name: string;
      description: string;
      serviceId: 0;
    };
  }
}