import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import {
  FormGroup,
  FormsModule,
  NonNullableFormBuilder,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { NzBreadCrumbModule } from 'ng-zorro-antd/breadcrumb';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzDividerModule } from 'ng-zorro-antd/divider';
import { NzGridModule } from 'ng-zorro-antd/grid';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzTableModule } from 'ng-zorro-antd/table';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzSelectModule } from 'ng-zorro-antd/select';
import { NzDatePickerModule } from 'ng-zorro-antd/date-picker';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzUploadModule } from 'ng-zorro-antd/upload';

@Component({
  selector: 'app-service',
  standalone: true,
  imports: [
    CommonModule,
    NzBreadCrumbModule,
    NzDividerModule,
    NzGridModule,
    NzInputModule,
    NzIconModule,
    NzButtonModule,
    NzTableModule,
    NzFormModule,
    FormsModule,
    ReactiveFormsModule,
    NzSelectModule,
    NzDatePickerModule,
    NzUploadModule,
  ],
  providers: [NzMessageService],
  template: `
    <nz-breadcrumb>
      <nz-breadcrumb-item>Quản lý dịch vụ</nz-breadcrumb-item>
      <nz-breadcrumb-item>Tạo dịch vụ</nz-breadcrumb-item>
    </nz-breadcrumb>
    <nz-divider></nz-divider>
    <div>
      <form nz-form>
        <div nz-row class="tw-ml-[12%]">
          <!-- Tên chi nhánh -->
          <nz-form-item nz-col nzSpan="12" class="">
            <nz-form-label class="tw-ml-3" nzRequired
              >Tên dịch vụ</nz-form-label
            >
            <nz-form-control nzErrorTip="Vui lòng nhập họ và tên đệm">
              <input
                class="tw-rounded-md tw-w-[70%]"
                nz-input
                placeholder="Nhập tên dịch vụ"
              />
            </nz-form-control>
          </nz-form-item>

          <!-- địa chỉ -->
          <nz-form-item nz-col nzSpan="12" class="">
            <nz-form-label class="tw-ml-3" nzRequired>Giá dịch vụ</nz-form-label>
            <nz-form-control nzErrorTip="Vui lòng nhập tên">
              <input
                class="tw-rounded-md tw-w-[70%]"
                nz-input
                placeholder="Nhập giá dịch vụ"
              />
            </nz-form-control>
          </nz-form-item>

          <nz-form-item nz-col nzSpan="12" class="">
            <nz-form-label class="tw-ml-3" nzRequired>Mô tả dịch vụ</nz-form-label>
            <nz-form-control nzErrorTip="Vui lòng nhập tên">
            <textarea  class="tw-w-[70%]" rows="5" nz-input></textarea>
            </nz-form-control>
          </nz-form-item>

          <nz-form-item nz-col nzSpan="12" class="">
            <nz-form-label class="tw-ml-3" nzRequired>Ảnh dịch vụ</nz-form-label>
            <nz-form-control nzErrorTip="Vui lòng nhập tên" class="tw-w-[70%]">
              <nz-upload
                nzType="drag"
                [nzMultiple]="true"
                nzAction="https://www.mocky.io/v2/5cc8019d300000980a055e76"

              >
                <p class="ant-upload-drag-icon">
                  <span nz-icon nzType="inbox"></span>
                </p>
                <p class="ant-upload-text">
                  Click or drag file to this area to upload
                </p>
              </nz-upload>
            </nz-form-control>
          </nz-form-item>

          <!-- dia chi -->
        </div>
        <nz-divider></nz-divider>
      </form>
      <div class="tw-text-center">
        <button nz-button nzDanger nzType="primary">Làm mới</button>
        <button nz-button nzType="primary" class="tw-ml-4">
          Tạo dịch vụ
        </button>
      </div>
    </div>
  `,
  styles: [],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ServiceComponent {}