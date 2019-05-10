import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Message} from "primeng/api";
import {AlimentService} from "../../../../core/services/aliment.service";
import {Aliment} from "../../../../core/models/aliment.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-aliment-creation',
  templateUrl: './aliment-creation.component.html',
  styleUrls: ['./aliment-creation.component.css']
})
export class AlimentCreationComponent implements OnInit {

  alimentCreationForm: FormGroup;
  uploadFile: any;
  imageForm: any;
  message: Message[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private alimentService: AlimentService
  ) {
  }

  ngOnInit() {
    this.alimentCreationForm = this.createFormGroup();
  }

  createFormGroup(): FormGroup {
    return this.formBuilder.group({
      name: [null, Validators.required],
      price: [null, Validators.required]
    });
  }

  revertFormGroup() {
    this.uploadFile = null;
    this.alimentCreationForm = this.createFormGroup();
    this.imageForm.clear();
  }

  save() {
    const alimentToCreate: Aliment = Object.assign({},
      this.alimentCreationForm.value);
    this.alimentService.add(alimentToCreate).subscribe(result=>
    {
      let aliment=JSON.parse(JSON.stringify(result));
      this.alimentService.addImage(aliment,this.uploadFile);
    });
  }


  onUpload(event,form){
    for(let file of event.files){
      this.uploadFile=file;
    }
    this.imageForm=form;
  }

  showSuccess() {
    this.message = [];
    this.message.push({severity:'info', summary:'Aliment Created'});
  }
}
